---
layout: default
title:  "How to install and run"
date:   2016-12-23 15:20:00
categories: main
---

Install java and set the JAVA_HOME on your environment variables:

[See how do it here]

Install maven to build the project:
{% highlight bash %}
sudo apt-get install maven
{% endhighlight %}

Clone the repository in your computer:

{% highlight bash %}
git clone https://github.com/ArthurJordao/minitwitter.git
{% endhighlight %}

Change directory to the project folder:
{% highlight bash %}
cd minitwitter
{% endhighlight %}

Install a DB and configure it on the application.properties of the project and add the driver on the pom.xml:
{% highlight bash %}
vi src/main/resources/application.properties
vi pom.xml
{% endhighlight %}
*Default DB config is mariadb with user root, password root and database name as jdbc:mysql://localhost:3306/minitwitter*

Build the project with maven:
{% highlight bash %}
mvn install
{% endhighlight %}

Execute the fatjar on target/:
{% highlight bash %}
java -jar target/minitwitter-0.0.1-SNAPSHOT.jar
{% endhighlight %}

[See how do it here]: https://docs.oracle.com/javase/tutorial/essential/environment/paths.html