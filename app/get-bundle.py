#!/usr/bin/env python

import urllib2
from urllib2 import HTTPError
import sys
import time
import json
import os
import shutil
import tarfile

if len(sys.argv) <= 1:
	print "Missing society identifier!"
	exit(1)

try:
	url = "http://api.stormcorp.co/v1.0/apps/" + str(sys.argv[1]) + "/bundle"
	print "Downloading from " + url
except KeyError:
	print "Society " + str(sys.argv[1]) + " does not exist!"
	exit(2)

try:
	bundle = urllib2.urlopen(url)

	with open(str(sys.argv[1]) + '_bundle.tar.gz', 'wb') as archive:
		archive.write(bundle.read())
except HTTPError, e:
	print "HttpError: " + str(e.code) + " " + url

bundle = tarfile.open(str(sys.argv[1]) + '_bundle.tar.gz', 'r:gz')

os.chdir("src/main/")
if os.path.exists("assets") and os.path.isdir("assets"):
	shutil.rmtree("assets")
os.makedirs("assets")
os.chdir("assets")

bundle.extractall(".")
os.chdir("../../..")
os.remove(str(sys.argv[1]) + "_bundle.tar.gz")
