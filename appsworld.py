#!/usr/bin/env python
import requests
import sys, getopt
import os
import subprocess
import boto
import re
import readline
import requests
from time import time
from boto.s3.key import Key
from random import randint

VERSION = "v1.0"

def restart_line():
	sys.stdout.write('\r')
	sys.stdout.flush()

def unescape(filename):
    return re.sub(r'(?<!\\)\\', '', filename)

def main(argv):
	name = None
	colour1 = None
	colour2 = None
	appid = None
	company = None
	colour2str = ""
	out_folder = str(time()) + "." + str(randint(0, 100))

	opts, args = getopt.getopt(argv, "n:c1:c2:a:cp:", ["name=", "colour1=", "colour2=", "appid=", "company="])

	for opt, arg in opts:
		if opt in ("--name"):
			name = arg.strip()
		elif opt in ("--colour1"):
			colour1 = arg.strip()
		elif opt in ("--colour2"):
			colour2 = arg.strip()
		elif opt in ("--appid"):
			appid = arg.strip()
		
	res_path = os.path.dirname(os.path.realpath(__file__))
	apk_path = res_path + "/app/build/outputs/apk/app-demo-release.apk"

	if name is None:
		name = raw_input("name: ").strip()

	if colour1 is None:
		colour1 = raw_input("colour1: ").strip()

	if colour2 is None:
		colour2 = raw_input("colour2: ").strip()

	if appid is None:
		appid = raw_input("appid: ").strip()

	if not name:
		print("name is required")
		sys.exit()

	if not appid:
		print("app id is required")
		sys.exit()

	if not colour1:
		colour1 = "#333333"

	if not colour2:
		colour2 = "#000000"

	colour2str = " -Papptint2=\"" + colour2 + "\""
	os.system(res_path + "/gradlew assembleRelease -Pappname=\"" + name + "\" -Papptint1=\"" + colour1 + "\" -Pappid=" + appid + colour2str)

	# upload apk
	try:
		# Connect to S3
		bucket = "roboto.cubeapis.com"
		sys.stdout.write('connecting to S3...')

		connection = boto.connect_s3(os.environ['AWS_ACCESS_KEY'], os.environ['AWS_SECRET_KEY'])

		# Connect to bucket
		restart_line()
		sys.stdout.write('accessing ' + bucket + '...')
		bucket = connection.create_bucket(bucket)

		# Upload data
		restart_line()
		sys.stdout.write('uploading ' + apk_path + '...')
		key = Key(bucket)
		key.key = 'appsworld-' + appid + '.apk'
		key.set_contents_from_filename(apk_path)

		key.set_acl('public-read')
		apk_url = key.generate_url(expires_in = 0, query_auth = False)

		print('\nupload complete')
	except Exception as e:
		restart_line()
		sys.stdout.write("AWS Connection error: {0}".format(e))
		sys.exit()

	apk_url = apk_url.replace("https", "http")

	resp = requests.get("https://3cu.be/yourls-api.php?username=admin&password=hXkuMOXTSL9xyLPrHhfkb3v4&action=shorturl&format=json&url=" + apk_url, verify=False)
	resp_data = resp.json()

	print "\n"
	print "============================="
	print "APK URL : " + resp_data["shorturl"]
	print "============================="

	os.system("rm -rf " + res_path + "/" + out_folder + ";")
	print("")

if __name__ == "__main__":
   main(sys.argv[1:])