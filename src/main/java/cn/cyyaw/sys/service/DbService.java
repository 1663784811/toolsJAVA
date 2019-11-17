package cn.cyyaw.sys.service;

import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

public interface DbService {

    @Transactional
    void readstructure(String url, String user, String password) throws SQLException, ClassNotFoundException;
}
