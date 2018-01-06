import com.mk.mongodb.bean.Person;
import com.mk.mongodb.dao.PersonDao;
import com.mk.util.JsonUtil;
import org.junit.Test;

import java.util.Date;

public class MongoTest {

    @Test
    public void savePerson(){
        for (int i = 0; i <43 ; i++) {
            Person person1 = new Person();
            person1.setAddress("keke"+i);
            person1.setDeptno(i%3);
            person1.setName("wangwang"+i);
            person1.setBirthday(new Date());
            person1.setMoney(12.5+i);
            person1 = PersonDao.save(person1);
            System.out.println("keketip===="+ JsonUtil.obj2String(person1));
        }
    }

    @Test
    public void updatePerson(){
        Person person = new Person();
        person.setId("5a4454a4d6029e3d496f4809");
        person.setName("徐柯");
        PersonDao.updateOne(person);
    }

    @Test
    public void updateMany(){
        Person person = new Person();
        person.setName("徐柯");
        PersonDao.updateMany(person,"wangwang");//把名字为wangwang的用户改成“徐柯”
    }


    @Test
    public void updateManyOr(){
        Person person = new Person();
        person.setName("徐柯11111");
        PersonDao.updateManyOr(person,"徐柯","keke3");//把名字为徐柯或者地址是keke3的信息改成“徐柯11111”
    }

    @Test
    public void getPerson(){
        Person person = PersonDao.getPerson("5a439ed6d60259e7c3a58fe7");
        System.out.println("keketip===="+ JsonUtil.obj2String(person));
    }

    @Test
    public void getPerson2(){
        Person person = PersonDao.getPerson2("5a439ed6d60259e7c3a58fe7");
        System.out.println("keketip===="+ JsonUtil.obj2String(person));
    }

    @Test
    public void getPerson3(){
        Person person = PersonDao.getPerson3("5a439ed6d60259e7c3a58fe7");
        System.out.println("keketip===="+ JsonUtil.obj2String(person));
    }

    @Test
    public void query(){
        PersonDao.query("wangwang");
    }


    @Test
    public void page(){
        PersonDao.page("wangwang",0,2);//第一页
        PersonDao.page("wangwang",2,2);//第二页
    }

    //删除ID为：5a4454a4d6029e3d496f480a的数据
    @Test
    public void delete(){
        PersonDao.deleteById("5a4454a4d6029e3d496f480a");
    }
}