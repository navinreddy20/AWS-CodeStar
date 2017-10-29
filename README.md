Welcome to the AWS CodeStar sample web application
==================================================

This sample code helps get you started with a simple Java web application
deployed by AWS Elastic Beanstalk.

What's Here
-----------

This sample includes:

* README.md - this file
* .ebextensions/ - this directory contains the Java configuration file that
  allows AWS Elastic Beanstalk to deploy your Java application
* buildspec.yml - this file is used by AWS CodeBuild to build the web
  application
* pom.xml - this file is the Maven Project Object Model for the web application
* src/ - this directory contains your Java application source files


Getting Started
---------------

These directions assume you want to develop on your local computer, and not
from the Amazon EC2 instance itself. If you're on the Amazon EC2 instance, the
virtual environment is already set up for you, and you can start working on the
code.

To work on the sample code, you'll need to clone your project's repository to your
local computer. If you haven't, do that first. You can find instructions in the
AWS CodeStar user guide.

1. Install maven.  See https://maven.apache.org/install.html for details.

2. Install tomcat.  See https://tomcat.apache.org/tomcat-7.0-doc/setup.html for
   details.

3. Build the application.

        $ mvn -f pom.xml compile
        $ mvn -f pom.xml package

4. Copy the built application to the Tomcat webapp directory.  The actual
   location of that directory will vary depending on your platform and
   installation.

        $ cp target/ROOT.war <tomcat webapp directory>

4. Restart your tomcat server

5. Open http://127.0.0.1:8080/ in a web browser to view your application.

What Do I Do Next?
------------------

Once you have a virtual environment running, you can start making changes to
the sample Java web application. We suggest making a small change to
/src/main/webapp/WEB-INF/views/index.jsp first, so you can see how changes
pushed to your project's repository are automatically picked up and deployed
to the Amazon EC2 instance by AWS Elastic Beanstalk. (You can watch the progress
on your project dashboard.) Once you've seen how that works, start developing
your own code, and have fun!

Learn more about AWS CodeStar by reading the user guide.  Ask questions or make
suggestions on our forum.

User Guide: http://docs.aws.amazon.com/codestar/latest/userguide/welcome.html

Forum: https://forums.aws.amazon.com/forum.jspa?forumID=248
