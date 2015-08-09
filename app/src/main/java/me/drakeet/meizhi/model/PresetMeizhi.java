package me.drakeet.meizhi.model;

import org.litepal.crud.DataSupport;

import java.util.List;

import me.drakeet.meizhi.model.Meizhi;

/**
 * Created by drakeet on 6/21/15.
 */
public class PresetMeizhi {

    public static boolean init() {
        List<Meizhi> qList = DataSupport.where("mid = ?", "2015/06/19").find(Meizhi.class);
        if (qList.size() > 0) {
            return true;
        }
        // 以下内容乃自动化生成，大家不用替俺担心~~
        new Meizhi("2015/03/26", "今天没有妹纸").save();
        new Meizhi("2015/03/30", "http://ww2.sinaimg.cn/large/610dc034gw1eqnjfdn45qj20h30mk443.jpg", 450, 594).save();
        new Meizhi("2015/03/31", "http://ww2.sinaimg.cn/large/610dc034gw1eqoqwkyy8cj20h20h10wz.jpg", 401, 400).save();
        new Meizhi("2015/04/01", "http://ww4.sinaimg.cn/large/610dc034gw1eqpx7qtursj20go0go779.jpg", 300, 300).save();
        new Meizhi("2015/04/02", "http://ww3.sinaimg.cn/large/610dc034gw1eqr2vp3xtcj20m80m8jwd.jpg", 400, 400).save();
        new Meizhi("2015/04/03", "http://ww3.sinaimg.cn/large/610dc034gw1eqs82kt4e9j20m80tnwjo.jpg", 400, 534).save();
        new Meizhi("2015/04/07", "http://ww3.sinaimg.cn/large/610dc034gw1eqwuw1t94yj20ga0ib405.jpg", 400, 450).save();
        new Meizhi("2015/04/08", "http://ww4.sinaimg.cn/large/610dc034gw1eqxzn23bc3j20m80etjuo.jpg", 500, 333).save();
        new Meizhi("2015/04/09", "http://ww2.sinaimg.cn/large/610dc034gw1eqz66m9qctj20go0okdif.jpg", 450, 663).save();
        new Meizhi("2015/04/10", "http://ww1.sinaimg.cn/large/610dc034gw1er0c8agag2j20m80bjt93.jpg", 549, 285).save();
        new Meizhi("2015/04/13", "http://ww2.sinaimg.cn/large/610dc034jw1er3t0hhn8dj20m80tn76d.jpg", 450, 600).save();
        new Meizhi("2015/04/14", "http://ww3.sinaimg.cn/large/610dc034gw1er4yt4dy15j20m80etwff.jpg", 500, 333).save();
        new Meizhi("2015/04/15", "http://ww1.sinaimg.cn/large/610dc034gw1er645i2y90j20hb0kimyr.jpg", 623, 738).save();
        new Meizhi("2015/04/16", "http://ww3.sinaimg.cn/large/610dc034gw1er79vdrfvqj20b40jraao.jpg", 400, 711).save();
        new Meizhi("2015/04/17", "http://ww2.sinaimg.cn/large/610dc034gw1er8fhea7vnj20b40deq3i.jpg", 400, 482).save();
        new Meizhi("2015/04/20", "http://ww3.sinaimg.cn/large/610dc034gw1erbum2ltm6j20go0caab3.jpg", 600, 442).save();
        new Meizhi("2015/04/21", "http://ww3.sinaimg.cn/large/610dc034gw1erd1rhreacj20m80efjsd.jpg", 640, 415).save();
        new Meizhi("2015/04/22", "http://ww2.sinaimg.cn/large/610dc034gw1ere7awhfj0j20go0b3dg8.jpg", 600, 399).save();
        new Meizhi("2015/04/23", "http://ww4.sinaimg.cn/large/610dc034gw1erfcxwxlvuj20m80gzmyw.jpg", 640, 489).save();
        new Meizhi("2015/04/24", "http://ww4.sinaimg.cn/large/610dc034gw1ergiue1xlbj20m80gq757.jpg", 450, 339).save();
        new Meizhi("2015/04/27", "http://ww3.sinaimg.cn/large/610dc034gw1erjtx9odarj20m80e2mxw.jpg", 640, 405).save();
        new Meizhi("2015/04/29", "http://ww3.sinaimg.cn/large/610dc034gw1erm9yr0v83j20m80snjt2.jpg", 450, 580).save();
        new Meizhi("2015/04/30", "http://ww4.sinaimg.cn/large/610dc034gw1erng5ktg5ij20m80eumy7.jpg", 450, 300).save();
        new Meizhi("2015/05/04", "http://ww1.sinaimg.cn/large/610dc034gw1ers1ue9tizj20m80euq3k.jpg", 480, 320).save();
        new Meizhi("2015/05/06", "http://ww3.sinaimg.cn/large/610dc034jw1erudbbww3xj20go0p075j.jpg", 480, 720).save();
        new Meizhi("2015/05/07", "http://ww1.sinaimg.cn/large/610dc034gw1ervje0eqqbj20b40gmjry.jpg", 384, 580).save();
        new Meizhi("2015/05/08", "http://ww3.sinaimg.cn/large/610dc034gw1erwpilp4kjj20go0p00tr.jpg", 480, 720).save();
        new Meizhi("2015/05/11", "http://ww1.sinaimg.cn/large/610dc034jw1es0jgf2v91j20go0p2ab1.jpg", 480, 722).save();
        new Meizhi("2015/05/12", "http://ww2.sinaimg.cn/large/610dc034gw1es1dap6rvgj20m80eugmi.jpg", 480, 320).save();
        new Meizhi("2015/05/13", "http://ww1.sinaimg.cn/large/610dc034jw1es2hkc090aj20go0p0dgu.jpg", 480, 720).save();
        new Meizhi("2015/05/14", "http://ww3.sinaimg.cn/large/610dc034jw1es3mty6nm2j20go0n60t9.jpg", 600, 834).save();
        new Meizhi("2015/05/15", "http://ww2.sinaimg.cn/large/610dc034gw1es4si7kzebj20m80eu0te.jpg", 450, 300).save();
        new Meizhi("2015/05/18", "http://ww3.sinaimg.cn/large/610dc034gw1es89uzch20j20pw0xcadb.jpg", 400, 515).save();
        new Meizhi("2015/05/19", "今天没有妹纸", 0, 0).save();
        new Meizhi("2015/05/20", "http://ww1.sinaimg.cn/large/7a8aed7bgw1esahpyv86sj20hs0qomzo.jpg", 400, 600).save();
        new Meizhi("2015/05/21", "http://ww2.sinaimg.cn/large/7a8aed7bgw1esbmanpn0tj20hr0qo0w8.jpg", 400, 601).save();
        new Meizhi("2015/05/22", "http://ww1.sinaimg.cn/large/7a8aed7bgw1escs1cl4f5j20qo0jsn3m.jpg", 450, 334).save();
        new Meizhi("2015/05/25", "http://ww4.sinaimg.cn/large/7a8aed7bgw1esfbgw6vc3j20gy0pedic.jpg", 480, 719).save();
        new Meizhi("2015/05/26", "http://ww4.sinaimg.cn/large/7a8aed7bgw1eshfawk6bmj20hs0qo41i.jpg", 640, 960).save();
        new Meizhi("2015/05/27", "http://ww1.sinaimg.cn/large/7a8aed7bgw1esijbxxwkfj20f00qotb6.jpg", 540, 960).save();
        new Meizhi("2015/05/28", "http://ww4.sinaimg.cn/large/7a8aed7bgw1esjpu1vxggj20qo0hrgqw.jpg", 650, 433).save(); // 代码家[笑哭]
        new Meizhi("2015/05/29", "http://ww3.sinaimg.cn/large/7a8aed7bgw1esk47n9j93j20hs0qoacp.jpg", 500, 750).save();
        new Meizhi("2015/06/01", "http://ww1.sinaimg.cn/large/610dc034jw1esofhleczfj20qo0hsgoq.jpg", 600, 373).save();
        new Meizhi("2015/06/02", "http://ww1.sinaimg.cn/large/7a8aed7bgw1esojpl5gmgj20qo0hstbs.jpg", 650, 433).save();
        new Meizhi("2015/06/03", "http://ww3.sinaimg.cn/large/7a8aed7bgw1esq1f0899qj20hs0qo780.jpg", 600, 900).save();
        new Meizhi("2015/06/04", "http://ww2.sinaimg.cn/large/7a8aed7bgw1esr71e2oulj20gz0pb40u.jpg", 550, 820).save();
        new Meizhi("2015/06/05", "http://ww2.sinaimg.cn/large/7a8aed7bgw1essxtqxs6jj20mj0tzdhl.jpg", 493, 655).save();
        new Meizhi("2015/06/08", "http://ww1.sinaimg.cn/large/7a8aed7bgw1eswem6zx1mj20qo0hrwgs.jpg", 650, 433).save();
        new Meizhi("2015/06/09", "http://ww4.sinaimg.cn/large/7a8aed7bgw1eswx1z5iu6j20hs0qo428.jpg", 600, 900).save();
        new Meizhi("2015/06/10", "http://ww1.sinaimg.cn/large/7a8aed7bgw1esxxiw20rej20qo0hstcp.jpg", 650, 433).save();
        new Meizhi("2015/06/11", "http://ww2.sinaimg.cn/large/7a8aed7bgw1esz3jq17foj20hs0qodj9.jpg", 550, 825).save();
        new Meizhi("2015/06/12", "http://ww4.sinaimg.cn/large/7a8aed7bgw1et11xp5wwij20hs0qotb2.jpg", 640, 960).save();
        new Meizhi("2015/06/15", "http://ww4.sinaimg.cn/large/7a8aed7bgw1et3qjtenw1j20qo0hsdj3.jpg", 650, 433).save();
        new Meizhi("2015/06/16", "http://ww3.sinaimg.cn/large/7a8aed7bgw1et5nl9mno8j20hs0qoacj.jpg", 450, 675).save();
        new Meizhi("2015/06/17", "http://ww1.sinaimg.cn/large/7a8aed7bgw1et6yio5s7rj20hs0qogop.jpg", 450, 675).save();
        new Meizhi("2015/06/18", "http://ww1.sinaimg.cn/large/7a8aed7bgw1et80fw2p80j20qo0hsdj1.jpg", 660, 440).save();
        new Meizhi("2015/06/19", "http://ww3.sinaimg.cn/large/7a8aed7bgw1et95oadpnjj20qo0hs0vk.jpg", 720, 480).save();
        new Meizhi("2015/06/26", "http://ww2.sinaimg.cn/large/610dc034jw1ethd8ez8yaj20go0p0gpm.jpg", 600, 900).save();
        new Meizhi("2015/06/29", "http://ww1.sinaimg.cn/large/7a8aed7bgw1etkpwkaxqej20gy0pfta8.jpg", 610, 915).save();
        new Meizhi("2015/06/30", "http://ww1.sinaimg.cn/large/610dc034jw1etlvfi6vxkj20h20o476h.jpg", 614, 868).save();
        new Meizhi("2015/07/01", "http://ww1.sinaimg.cn/large/7a8aed7bgw1etn2gzjoegj20gz0p9400.jpg", 455, 677).save();
        new Meizhi("2015/07/02", "http://ww3.sinaimg.cn/large/7a8aed7bgw1etlw75so1hj20qo0hsgpk.jpg", 550, 367).save();
        new Meizhi("2015/07/03", "http://ww1.sinaimg.cn/large/7a8aed7bgw1etpfol394kj20qo0hsdiw.jpg", 650, 433).save();
        new Meizhi("2015/07/04", "今天没有妹纸").save();
        new Meizhi("2015/07/05", "今天没有妹纸").save();
        new Meizhi("2015/07/06", "http://ww1.sinaimg.cn/large/7a8aed7bgw1etstfoej4mj20ey0migom.jpg", 538, 810).save();
        new Meizhi("2015/07/07", "http://ww1.sinaimg.cn/large/7a8aed7bgw1ettzpowndgj216g0s4dkg.jpg", 650, 430).save();
        new Meizhi("2015/07/08", "http://ww3.sinaimg.cn/large/7a8aed7bgw1etv4f9fro4j20hs0qowg6.jpg", 550, 825).save();
        new Meizhi("2015/07/09", "http://ww2.sinaimg.cn/large/7a8aed7bgw1etwa7ayf35j20j60csmzb.jpg", 650, 433).save();
        new Meizhi("2015/07/10", "http://ww1.sinaimg.cn/large/7a8aed7bgw1etxg3kjmelj20hs0qoq60.jpg", 550, 825).save();
        new Meizhi("2015/07/11", "今天没有妹纸").save();
        new Meizhi("2015/07/12", "今天没有妹纸").save();
        new Meizhi("2015/07/13", "http://ww1.sinaimg.cn/large/7a8aed7bgw1eu0w2y0hfbj20hs0qoadi.jpg", 640, 960).save();
        new Meizhi("2015/07/14", "http://ww4.sinaimg.cn/large/7a8aed7bgw1eu22nypk9hj20dw0kutc0.jpg", 500, 750).save();
        new Meizhi("2015/07/15", "http://ww3.sinaimg.cn/large/610dc034gw1eu3bld296jj20df0kudhx.jpg", 483, 750).save();
        new Meizhi("2015/07/16", "http://ww2.sinaimg.cn/large/7a8aed7bgw1eu4cwbv2a6j20zk0qo7dw.jpg", 650, 488).save();
        new Meizhi("2015/07/17", "http://ww3.sinaimg.cn/large/7a8aed7bgw1eu5jpf3lamj20f00mitck.jpg", 540, 810).save();
        new Meizhi("2015/07/18", "今天没有妹纸").save();
        new Meizhi("2015/07/19", "今天没有妹纸").save();
        new Meizhi("2015/07/20", "http://ww3.sinaimg.cn/large/7a8aed7bgw1eu8zauvndoj20go0gpdik.jpg", 450,451).save();
        new Meizhi("2015/07/21", "http://ww1.sinaimg.cn/large/7a8aed7bgw1eua4mbsim0j20hs0qojuh.jpg", 480,720).save();
        new Meizhi("2015/07/22", "http://ww2.sinaimg.cn/large/610dc034gw1eubet6h43qj20qo0hsadh.jpg", 850,567).save();
        new Meizhi("2015/07/23", "http://ww2.sinaimg.cn/large/7a8aed7bjw1eucg4wdq8ij20m80m8q6s.jpg", 800,800).save();
        new Meizhi("2015/07/24", "http://ww3.sinaimg.cn/large/7a8aed7bgw1eudm5ktieij20ey0midhq.jpg", 480,723).save();
        new Meizhi("2015/07/25", "今天没有妹纸").save();
        new Meizhi("2015/07/26", "今天没有妹纸").save();
        new Meizhi("2015/07/27", "http://ww2.sinaimg.cn/large/7a8aed7bgw1euh2zyekn2j20hs0qodje.jpg", 480,720).save();
        new Meizhi("2015/07/28", "http://ww3.sinaimg.cn/large/7a8aed7bgw1eui8h92qyaj20p111idmw.jpg", 480,719).save();
        new Meizhi("2015/07/29", "http://ww3.sinaimg.cn/large/610dc034gw1eujhf1599jj20dt0kuwhi.jpg", 497,750).save();
        new Meizhi("2015/07/30", "http://ww4.sinaimg.cn/large/7a8aed7bgw1eujhfwyj27j20qo0hs0vy.jpg", 880,587).save();
        new Meizhi("2015/07/31", "http://ww2.sinaimg.cn/large/7a8aed7bgw1eukj6vosygj20gs0p0act.jpg", 604,900).save();
        new Meizhi("2015/08/01", "今天没有妹纸").save();
        new Meizhi("2015/08/02", "今天没有妹纸").save();
        new Meizhi("2015/08/03", "http://ww3.sinaimg.cn/large/7a8aed7bgw1eup75gxp8qj20hr0qoae8.jpg", 600,901).save();
        new Meizhi("2015/08/04", "http://ww1.sinaimg.cn/large/7a8aed7bgw1euqcfwjbkdj20hs0qo40w.jpg", 480,720).save();
        new Meizhi("2015/08/05", "http://ww4.sinaimg.cn/large/7a8aed7bgw1eurhwfc5z7j20hs0qomzz.jpg", 640,960).save();
        new Meizhi("2015/08/06", "http://ww4.sinaimg.cn/large/7a8aed7bgw1eusn3niy2bj20hs0qo0wb.jpg", 640,960).save();
        new Meizhi("2015/08/07", "http://ww2.sinaimg.cn/large/7a8aed7bgw1eutsd0pgiwj20go0p0djn.jpg", 500,750).save();

        return true;
    }

}
