# JavaRcon

**JavaRcon** is a simple **java based remote console tool** primarly intended for sending commands to Minecraft servers remotely. Game server must have rcon enabled in order for this tool to work.

You can use this program compiled or you can also compile it yourself by using the included source code. Also you can download the latest compiled version from relises !

## Getting Started

I will give you a short tutorial on how to set up this tool on a Debian distribution called Ubuntu Server (these steps should be same for any Debian based Linux distro). I recommend using Debian distribution called Ubuntu Server for any type of server hosting.
More detailed guide will be available here: [JavaRcon](https://pequla.github.io/projects/javarcon/index.html)

### Prerequisites (Windows)

You will need to visit [Java download page](https://www.java.com/en/download/manual.jsp) and download the latest java software for Windows and install it! If you already had java installed skip this part.

Now lets check if the java installed correctly by typing in Command Prompt:

```
java -version
```

If it returns something like this you are ok:

```
java version "1.8.0_241"
Java(TM) SE Runtime Environment (build 1.8.0_241-b07)
Java HotSpot(TM) 64-Bit Server VM (build 25.241-b07, mixed mode)
```
NOTE: You might have different java version installed on your computer, at the time of creating this file Java 8u241 was the latest reliese

### Prerequisites (Debian\Ubuntu)

First of all we need to download (or make sure we have) Java 8 installed on our server instance. To do that type in:

```
sudo apt update
sudo apt install openjdk-8-jre
```

If the terminal returns:

```
$ openjdk-8-jre is already the newest version
```

You already had Java 8 installed (and that is expected since Minecraft server requries Java 8 too). 

### Installing

Download the latest relase from [here](https://github.com/Pequla/JavaRcon/releases) and extract its content !

If you are on widnows just edit "config.properties" and than run the tool with run.bat

This is how your config will look like by default (plase change the parametares to fit your server):

```
#JavaRcon configuration file.
#Created by: Pequla ( https://pequla.github.io/ )
#Thu Mar 12 23:38:50 CET 2020
rcon.password=password
rcon.port=25575
rcon.host=localhost
```

But if you are on Debian distros such as Ubuntu you will, alongside editing that config file, need to rename the run.bat file to run.sh and then use terminal to make it executable:

```
sudo rn run.bat run.sh
sudo chmod u+x run.sh
```

NOTE: If you get an error after trying to run the run.sh file with ./run.sh please make these files executable too:

```
sudo chmod u+x JavaRcon.jar
sudo chmod u+x /lib/rkon*
```

## Libraries

* [rkon-core](https://github.com/Kronos666/rkon-core) - Source RCON protocol library made by Kronos666

## Author

* **Petar Kresoja** - *University Singidunum - Faculty of Informatics and Computing* - [Pequla](https://github.com/Pequla)
