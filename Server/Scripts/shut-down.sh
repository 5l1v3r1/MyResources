#!/usr/bin/env bash

. /etc/init.d/functions || exit 1

NAME=
VERSION=0.0.1-SNAPSHOT

PIDFILE=/var/run/${NAME}.pid

DAEMON=java

if [ -e ${PIDFILE} ]; then
  PID=`pidofproc -p ${PIDFILE} ${DAEMON}`
  RETVAL=$?
  if [ ${RETVAL} -eq 0 ]; then
    if killproc -p ${PIDFILE} -d 300 ${DAEMON} ; then
      echo "Stopping ${NAME} (${PID})"
    else
      echo "Failed to stop ${NAME} (${PID})"
    fi
    /bin/rm -f ${PIDFILE}
else
  echo "Not running"
fi
