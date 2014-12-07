package org.alma.gl



/**
 * @author E120404Z
 */
class Undo (w : Workspace) extends Command(w) {
  
  override def execute() : Unit={
    var mem : Memento = work.gardien.goToPreviousMemento()
    if(mem != null){
      work.restaurerDepuisMemento(mem)
    } 
  }
  
}