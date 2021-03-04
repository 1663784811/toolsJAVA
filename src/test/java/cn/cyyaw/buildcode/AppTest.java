package cn.cyyaw.buildcode;

import cn.cyyaw.buildcode.croe.controller.CodeController;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class AppTest {


    @Test
    public void shouldAnswerWithTrue() throws SQLException, IOException, ClassNotFoundException {

        CodeController codeController = new CodeController();

        codeController.buildCode("jdbc:mysql://127.0.0.1:3306/smartpark", "root", "because", null);

    }
}
