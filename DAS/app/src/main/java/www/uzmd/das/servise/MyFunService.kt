package www.uzmd.das.servise

import www.uzmd.das.KitobModell
import www.uzmd.das.UstozModell

interface MyFunService {
    fun addUstoz(u: UstozModell)
    fun editUstoz(u: UstozModell)
    fun deletUstoz(u: UstozModell)
    fun listUstoz(): List<UstozModell>
    //yangi metod bu metod o'zida ustozda kiritilgan fanlar idisini saqlaydi va list qaytaradi
    fun getByIdUstozList():List<Int>
    fun getByIdMavzularList():List<Int>
    /*Shogird*/
    fun addKitob(u: KitobModell)
    fun listKitob(id:Int):List<KitobModell>

}