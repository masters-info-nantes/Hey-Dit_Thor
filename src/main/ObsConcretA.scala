class ObsConcretA(s : Subject) extends Observer(s){
  
  override def receiveUpdate( s:Subject) : Unit={
    sujet = s
    s.affiche
  }
}