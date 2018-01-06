import com.mk.mongodb.bean.UserLog;
import com.mk.mongodb.dao.IUserLogDao;
import com.mk.mongodb.dao.impl.UserDaoImpl;
import com.mk.util.JsonUtil;
import org.junit.Test;

import java.util.Date;

public class UserLogTest {

    @Test
    public void save(){
        IUserLogDao userLogDao = new UserDaoImpl();
        UserLog userLog = new UserLog();
        userLog.setBos("windows");
        userLog.setCreateTime("2017-2-12");
        userLog.setInfo("java");
        userLog.setIp("175.54.44.45");
        userLog.setMessage("keke");
        userLog.setModel(1);
        userLog.setUserId(1);
        userLog.setUpdateTime(new Date());
        userLog.setUsername("柯柯");
        userLogDao.save(userLog);
    }


    @Test
    public void getById(){
        IUserLogDao userLogDao = new UserDaoImpl();
        UserLog userLog =  userLogDao.getById("5a4b944ed602890ae9a2f735");
        System.out.println("keketip===="+ JsonUtil.obj2String(userLog));
    }
}
