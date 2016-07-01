#!/usr/bin/env bash

. /etc/init.d/functions || exit 1

NAME=
VERSION=

PIDFILE=/var/run/${NAME}.pid

DAEMON=java

if [ -e ${PIDFILE} ]; then
  PID=`pidofproc -p ${PIDFILE} ${DAEMON}`
  RETVAL=$?
  if [ ${RETVAL} -eq 0 ]; then
    echo "Already running (${PID})"
    exit 0
  fi
fi

# dissect the path of this script
PRGDIR=`dirname "$0"`
PRGDIR=`cd "${PRGDIR}"; pwd`
ZIPDIR=`dirname "${PRGDIR}"`
OPTDIR=`dirname "${ZIPDIR}"`

export CLASSPATH=${OPTDIR}/conf/${NAME}:${OPTDIR}/conf:${ZIPDIR}/app:${ZIPDIR}/app/lib
${DAEMON} -jar ${ZIPDIR}/app/${NAME}-${VERSION}.jar > /var/log/${NAME}-output.log 2>&1 &

RETVAL=$?
PID=$!
if [ ${RETVAL} -ne 0 ]; then
  echo "Start failed: ${RETVAL}"
  exit ${RETVAL}
fi
echo "${PID}" >${PIDFILE}
sleep 2
PID=`pidofproc -p ${PIDFILE} ${DAEMON}`
RETVAL=$?
if [ ${RETVAL} -eq 0 ]; then
  echo "Started ${NAME} (${PID})"
else
  echo "Start failed: ${RETVAL}"
  /bin/rm -f ${PIDFILE}
fi
