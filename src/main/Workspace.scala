
/**
 * @author 
 */
class Workspace extends Subject{
  
  var buffer : Buffer = new Buffer()
  var clipBoard : ClipBoard = new ClipBoard() 
  var gardien : Gardien = new Gardien()
  var cmd : Command = null
  var selection : Selection = new Selection()
  
  //var str : String = null
  
  override def affiche() : Unit={
    buffer.afficher
  }

  def sauverDansMemento : Memento={
    return new Memento(buffer,cmd)
  }
  
  def restaurerDepuisMemento(m : Memento) : Unit={
     buffer = m.getBufferApres().clone()
     cmd = m.getCmd
  }

  def executeCommande(c : Command) : Unit={

    cmd = c
    var mem : Memento = sauverDansMemento
    gardien.addMemento(mem)
    c.execute
    notifyObservers
  }
  
  def reexecuteCommande() : Unit={
    executeCommande(cmd)
  }
  
  def afficheSelection() :Unit={
    println(selection.getContenu)
  }
  
}