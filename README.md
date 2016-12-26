# minitwitter
A social network inspired by twitter.
Made with spring boot.

[Blog Page](https://arthurjordao.github.io/minitwitter/)

##How to install and run

Install java and set the JAVA_HOME on your environment variables:

[See how do it here]

Install maven to build the project:
```
sudo apt-get install maven
```

Clone the repository in your computer:

```
git clone https://github.com/ArthurJordao/minitwitter.git
```

Change directory to the project folder:
```
cd minitwitter
```

Install a DB and configure it on the application.properties of the project and add the driver on the pom.xml:
```
vi src/main/resources/application.properties
vi pom.xml
```
*Default DB config is mariadb with user root, password root and database name as jdbc:mysql://localhost:3306/minitwitter*

Build the project with maven:
```
mvn install
```

Execute the fatjar on target/:
{% highlight bash %}
java -jar target/minitwitter-0.0.1-SNAPSHOT.jar
{% endhighlight %}

[See how do it here]: https://docs.oracle.com/javase/tutorial/essential/environment/paths.html
