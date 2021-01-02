package cn.cyyaw.util.buildcode;

import cn.cyyaw.util.buildcode.controller.CodeController;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class AppTest {


    @Test
    public void shouldAnswerWithTrue() throws SQLException, IOException, ClassNotFoundException {

        CodeController codeController = new CodeController();

        codeController.buildCode("jdbc:mysql://127.0.0.1:3306/cyyaw_store", "root", "because", null);

    }
}
