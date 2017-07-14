#!/bin/sh
#
# Copyright (C) 2005 Brett Lee <brett@brettlee.com> - Card Games Example in Java
# 
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
# 
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
# 
# You should have received a copy of the GNU General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.
#


# Run from this directory, like:
# ./build.sh


#
# Compile .java -> .class
# ----------------------------------

mkdir -p scratch

javac -d scratch ../src/com/brettlee/cardgames/*.java

#
# Build executable Jar file
# ----------------------------------

cd scratch

jar cmf ../../src/META-INF/MANIFEST.MF ../cardgames.jar com/brettlee/cardgames/*.class

#
# List executable Jar file
# ----------------------------------

cd ..
ls -l cardgames.jar

