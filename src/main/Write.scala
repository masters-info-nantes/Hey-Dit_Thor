class Write (w : Workspace,var s:String) extends Command(w){
  
  override def execute() : Unit={
    work.buffer.ecrire(s)
  }
}