sbtResolver <<= (sbtResolver) { r =>
  Option(System.getenv("SBT_PROXY_REPO")) map { x =>
    Resolver.url("proxy repo for sbt", url(x))(Resolver.ivyStylePatterns)
  } getOrElse r
}

resolvers <<= (resolvers) { r =>
  (Option(System.getenv("SBT_PROXY_REPO")) map { url =>
    Seq("proxy-repo" at url)
  } getOrElse {
    r ++ Seq(
      "twitter.com" at "http://maven.twttr.com/",
      "scala-tools" at "http://scala-tools.org/repo-releases/",
      "maven" at "http://repo1.maven.org/maven2/",
      "freemarker" at "http://freemarker.sourceforge.net/maven2/",
      "clojars" at "http://clojars.org/repo/"
    )
  }) ++ Seq("local" at ("file:" + System.getProperty("user.home") + "/.m2/repo/"))
}

externalResolvers <<= (resolvers) map identity

addSbtPlugin("com.twitter" %% "sbt-package-dist" % "1.0.5")

libraryDependencies += "com.twitter" %% "scalatest-mixins" % "1.0.3"
