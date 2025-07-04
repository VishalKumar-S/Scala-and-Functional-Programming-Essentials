package notes

object _6enums {

  enum fileActions(keyboardShortcut: String) {
    case READ extends fileActions("CTRL+R")
    case WRITE extends fileActions("CTRL+W")
    case DELETE extends fileActions("CTRL+D")
    case NONE extends fileActions("vjbskbsk")

    def openFile(): Unit = {
      if (this == READ) {
        println("Opening file...")
      }
      else {
        println("File access denied due to wrong instance access")
      }
    }

  }


  def accessFileActionInstances(keyboardShortcut: String):fileActions = {
    if (keyboardShortcut == "CTRL+R")  fileActions.READ
    else if (keyboardShortcut == "CTRL+W") fileActions.WRITE
    else if (keyboardShortcut == "CTRL+D") fileActions.DELETE
    else fileActions.NONE
  }

  def main(args:Array[String]):Unit =  {
      println("Enums, are  sepcial datatypes, which contains constants, which acts as finite instances ot teh enums, u can instatntiate or create a enw isntance to a enum, u can only used teh already fixed cosntatns in teh enusm, that are the only way u can use isntance of enums. Enums helps in having only finite set of posisbilties, so its useful in areas, where having only finfite set fo optiosn is safe, it also used to represent protocol states, configuration options, or command sets, ensuring only valid values are used.")

      val readFile1: fileActions = accessFileActionInstances("CTRL+R")
      readFile1.openFile()

      val readFile2: fileActions = accessFileActionInstances("CTRL+W")
      readFile2.openFile()

      val writeFile = fileActions.valueOf("WRITE")
      println("Ordinal is used ot know the index position of the  enum constants")
      val arrayOfEnumValues = fileActions.values
      for (x <- arrayOfEnumValues){
        println(s"Index: ${x.ordinal}\n Value: ${x}")
      }

  }

}
