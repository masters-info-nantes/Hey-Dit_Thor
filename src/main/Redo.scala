

/**
 * @author E120404Z
 */
class Redo (w : Workspace) extends Command(w) {
  
    override def execute() : Unit={
      var mem : Memento = w.gardien.redoLastCmd()
      if(mem != null){
        w.restaurerDepuisMemento(mem)
      }   
  }
}