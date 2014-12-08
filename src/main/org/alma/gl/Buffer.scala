package org.alma.gl

/**
 * Zone de texte contenant un texte et une selection
 * @author E120404Z
 * 
 */
class Buffer {
  private var zone : String = ""
  private var curseurCourant : SimpleCursor = new SimpleCursor(0)
   
  // genere un clone du buffer
  override def clone() : Buffer={ 
      var buf : Buffer = new Buffer
      buf.zone = this.zone
      buf.curseurCourant = this.curseurCourant.clone
      return buf
    }
  
  def getZone() : String={
    return zone
  }
  
  def setZone(s: String) : Unit={
    zone = s
  }
  
  def getCurseurCourant() : SimpleCursor={
    return curseurCourant
  }
  
 //insere la string en parametre a l'emplacement du curseur
  def ecrire(s:String) : Unit={
    
    var str : String = ""
    if(curseurCourant.getPos ==0){
      str = s + zone
      curseurCourant.setPos(s.length())
    }else if (curseurCourant.getPos == (zone.length())){
      str = zone + s
      curseurCourant.setPos(str.length())
    }else{
      
      for(x <- 0 to (curseurCourant.getPos-1))
        {str += zone(x)}
      str += s
      var temp : Int = curseurCourant.getPos
      curseurCourant.setPos(str.length())
      for(y <- temp to (zone.length()-1))
        {str += zone(y)}   
      
    }
    zone = str   
  }
  
 def afficher() : Unit={
   //println("pos cur:" + curseurCourant.getPos())
    val x : Int = 1
    var str : String = ""
    
    for (x <-0 to zone.length()-1){
      if((x== (curseurCourant.getPos))&&(x!=0)){
         str = str + "|"
         str = str + zone.charAt(x)
      }else{
        str = str + zone.charAt(x)
      }
    }
    if(curseurCourant.getPos > (zone.length()-1))
    {
      str = str + "|"
    }else if(curseurCourant.getPos ==0){
      str = "|" + str
    }
    println(str)  
  }
  
 
  def deplaceCurseurCourant (n : Int): Unit={
    //si l'entier passer en aparametre est trop grand on place
    //le curseur a la fin du texte
    if(n> zone.length())
      { curseurCourant.setPos(zone.length()) }
    else{
      //si negatif on place le curseur au debut du texte
      if(n<0)
        {curseurCourant.setPos(0)}
      else
        {curseurCourant.setPos(n)}
    }
    //println("pos cur:"+ curseurCourant.getPos)
  }
}