java -Xms512M -Xmx512M -Xss1M -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=384M -jar `dirname $0`/lib/sbt-launch.jar "$@"
