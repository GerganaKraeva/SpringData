import entities.Order;
import entities.User;
import orm.EntityManager;
import orm.MyConnector;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
     public static void main(String[] args) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
          MyConnector.createConnection("root", "root","mini_orm");
          Connection connection=MyConnector.getConnection();

          EntityManager <User> userEntityManager = new EntityManager<>(connection);
          User pesho = new User("pesho",43, LocalDate.now());
          pesho.setId(1);
          userEntityManager.persist(pesho);

         Iterable <User> users = userEntityManager.find(User.class, "age > 40");
          System.out.println(users.iterator().next());


//          EntityManager<Product>productEntityManager = new EntityManager<>(connection);
//          Product pen = new Product("pen",2.34);
//
//          productEntityManager.persist(pen);

         EntityManager <Order> orderEntityManager = new EntityManager<>(connection);
         Order order = new Order("mn123", LocalDate.now());

         orderEntityManager.persist(order);

     }
}
