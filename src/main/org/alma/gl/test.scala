package org.alma.gl

/**
 * @author E120404Z
 */
object test {
  
  def main(args: Array[String]) {

    var w : Workspace = new Workspace()
    var obs : ObsConcretA = new ObsConcretA(w)
    w.addObserver(obs)
    var selec : Selectionner = null
    var deb : Int =0
    var end : Int =0
    // w.affiche
     //pour selectionner faire un controle de string vide
      println("a pour ne rien faire , c pour copier  , v pour coller,x pour cut, m  pour selectionner, d pour deplacer le curseur , e pour ecrire, u undo")
      println("r redo, mac pour definir une macro,re pour reexecuter une commande")
      var choix : String = readLine()
      while(choix != "a"){
        if (choix == "c"){
          w.executeCommande(new Copy(w))
        } else if(choix == "v") {
          w.executeCommande(new Paste(w))
        } else if(choix == "re") {
          w.reexecuteCommande()
        }else if(choix == "r") {
          w.executeCommande(new Redo(w))
        }else if(choix == "u") {
          w.executeCommande(new Undo(w))
        }else if(choix == "mac") {
          var mac = new Macro(w)
          var choix : String =null
          while(choix!="q"){
            println("c pour copy,p pour paste,u pour undo,r pour redo,e pour ecrire,x pour cut, q pour quitter")
            choix = readLine()
            if(choix =="c"){new Copy(w) }
            else if (choix=="p"){mac.addCommand(new Paste(w))}
            else if(choix == "u"){mac.addCommand(new Undo(w))}
            else if(choix == "r"){mac.addCommand(new Redo(w))}
            else if(choix == "e"){println("Entrez votre texte")
          var ln : String = readLine()
          mac.addCommand(new Write(w,ln))}
            else if(choix == "x"){mac.addCommand(new Cut(w))}
          }
          w.executeCommande(mac)
          
        }else if(choix == "x"){
          w.executeCommande(new Cut(w))
        }else if(choix == "d"){
          println("Ou ?")
          var ou = readInt()
         w.executeCommande(new MoveCursor(w,ou))
        }else if(choix == "e"){
          println("Entrez votre texte")
          var ln : String = readLine()
          w.executeCommande(new Write(w,ln))
        }else if(choix == "m"){
          
          println("q pour sortir de la selection , md pour bouger le curseur de debut, mf pour bouger le cuseur de fin ")
          var choix : String = readLine()
          while (choix != "q"){
            if (choix == "md"){
              println("Ou ?")
               deb = readInt()
            }else if (choix == "mf"){
              println("Ou ?")
              end = readInt()
            }
            selec = new Selectionner(w,deb,end)
            w.executeCommande(selec)
            w.afficheSelection()
            choix = readLine()
                
          }
          w.executeCommande(selec)
 
          println("vous avez selectionne :" + w.selection.getContenu())
          
      println("a pour ne rien faire , c pour copier  , v pour coller,x pour cut, m  pour selectionner, d pour deplacer le curseur , e pour ecrire, u undo, r redo")
        }
      //  w.affiche
        choix = readLine()
        
      }
  }
}