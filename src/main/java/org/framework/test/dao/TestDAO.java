package org.framework.test.dao;

import org.framework.annotation.Repository;

/**
 * @author Bosen
 * @date 2021/9/11 22:29
 */
@Repository
public class TestDAO {
    public String echo() {
        return "This is TestDAO#echo!!!";
    }
}