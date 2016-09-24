/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import core.GenericDAOJpaImpl;
import entities.Role;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Hai Nguyen
 */
public class RoleDAO extends GenericDAOJpaImpl<Role, BigDecimal> {
    public static void main(String[] args) {
        RoleDAO dao = new RoleDAO();
        List<Role> r = dao.read();
        if (r != null) {
            for (Role role : r) {
                System.out.println(role);
            }
        }
    }
}
