#
# cdp4j Commercial License
#
# Copyright 2017, 2019 WebFolder OÜ
#
# Permission  is hereby  granted,  to "____" obtaining  a  copy of  this software  and
# associated  documentation files  (the "Software"), to deal in  the Software  without
# restriction, including without limitation  the rights  to use, copy, modify,  merge,
# publish, distribute  and sublicense  of the Software,  and to permit persons to whom
# the Software is furnished to do so, subject to the following conditions:
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR  IMPLIED,
# INCLUDING  BUT NOT  LIMITED  TO THE  WARRANTIES  OF  MERCHANTABILITY, FITNESS  FOR A
# PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL  THE AUTHORS  OR COPYRIGHT
# HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
# CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
# OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
#m

cmake_minimum_required(VERSION 3.8)

set (CMAKE_MODULE_PATH "${CMAKE_MODULE_PATH};${CMAKE_CURRENT_SOURCE_DIR}/CMake")

include(combine)

if(DEFINED ENV{VCPKG_ROOT} AND NOT DEFINED CMAKE_TOOLCHAIN_FILE)
  set(CMAKE_TOOLCHAIN_FILE "$ENV{VCPKG_ROOT}/scripts/buildsystems/vcpkg.cmake"
      CACHE STRING "")
endif()

project(cdp4j-libuv)

set(CMAKE_C_STANDARD 11)
set(CMAKE_POSITION_INDEPENDENT_CODE ON)
set(CMAKE_SUPPRESS_REGENERATION ON)

find_package(JNI REQUIRED)
find_package(unofficial-libuv CONFIG REQUIRED)

if(CMAKE_CXX_COMPILER_ID MATCHES "MSVC")
  set(CMAKE_CXX_FLAGS_RELEASE "/MD")
  set(CMAKE_CXX_FLAGS_DEBUG "/ZI /Od /MDd")
  add_definitions(-D_WIN32_WINNT=0x0601)
  find_package(Threads REQUIRED)
endif()

INCLUDE_DIRECTORIES(${JAVA_INCLUDE_PATH})
INCLUDE_DIRECTORIES(${JAVA_INCLUDE_PATH2})

file(GLOB_RECURSE SRC_FILES "*.h" "*.c")

list(FILTER SRC_FILES EXCLUDE REGEX ".*CMakeCXXCompilerId.cpp$")
list(FILTER SRC_FILES EXCLUDE REGEX ".*CMakeCCompilerId.c$")
list(FILTER SRC_FILES EXCLUDE REGEX ".*feature_tests.c$")
list(FILTER SRC_FILES EXCLUDE REGEX ".*feature_tests.cxx$")

if(MSVC)
  add_library(cdp4j STATIC ${SRC_FILES})
  target_link_libraries(cdp4j Threads::Threads unofficial::libuv::libuv)
else()
  add_library(uvcdp4j STATIC ${SRC_FILES})
  target_link_libraries(uvcdp4j unofficial::libuv::libuv)
  bundle_static_library(uvcdp4j cdp4j)
endif()
