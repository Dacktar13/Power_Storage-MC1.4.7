Power_Storage
=============

## Welcome to PowerStorage on GitHub

### Contributing
This is the property of Dacktar13. [here](https://github.com/Dacktar13/Power_Storage). If you wish to submit a pull request to fix bugs 
or broken behaivor feel free to do so. If you would like to add features or change existing behaivour or balance, please 
discuss it with Dacktar13 before submiting the pull request.


### Compiling and packaging PowerStorage
1. Ensure that `Apache Ant` (found [here](http://ant.apache.org/)) is installed correctly on your system.
 * Linux users will need the latest version of astyle installed as well.
1. Create a base directory for the build
1. Clone the PowerStorage repository into `basedir/PowerStorage /`
1. Download and copy the Buildcraft common dir (found [here](https://github.com/BuildCraft/BuildCraft)) into `basedir/PowerStorage /`
1. Copy the minecraft bin dir and minecraft_server.jar into `basedir/jars/`
1. Navigate to basedir/PowerStorage in a shell and run `ant` (this will take 2-5 minutes)
1. The compiled and obfuscated jar will be in basedir/build/dist

Your directory structure should look like this:
***

    basedir
    \- jars
     |- minecraft_server.jar
     \- bin
      |- minecraft.jar
      |- ...
    \- PowerStorage
     |- resources
     |- buildcraft
     |- common
     |- ...
  

***

