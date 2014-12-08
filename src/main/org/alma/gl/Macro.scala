package org.alma.gl

import scala.collection.mutable.ListBuffer

/**
 * Commande composee de plusieurs commandes
 * @author E120404Z
 */
class Macro (w: Workspace) extends Command(w){
  
  var mac : ListBuffer[Command]= new ListBuffer[Command]
  
  override def execute() : Unit={
    for(x <- 0 to (mac.length -1)){
      work.executeCommande(mac(x))
    }
  }
  
  def addCommand(c:Command) : Unit={
    mac += c
  }
  
  def removeCommand(i :Int) : Unit={
    mac.remove(i)
  }
}