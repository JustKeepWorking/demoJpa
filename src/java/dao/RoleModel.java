/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import core.HibernateUtil;
import entities.Role;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Hai Nguyen
 */
public class RoleModel {
    public List<Role> read() {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        return s.createCriteria(Role.class).list();
    }
    public static void main(String[] args) {
        RoleModel model = new RoleModel();
        List<Role> r = model.read();
        if (r != null) {
            r.stream().forEach((_item) -> {
                System.out.println(r);
            });
        } else {
            System.out.println("NULL");
        }
    }
}
