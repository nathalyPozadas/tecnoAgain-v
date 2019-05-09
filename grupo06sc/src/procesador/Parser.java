/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesador;

/**
 *
 * @author Brian
 */
public class Parser {

    private AnalizadorLex analex;
    public boolean errorFlag;

    public Parser(AnalizadorLex analex) {
        this.analex = analex;
    }

    public void Expresion() {
        this.errorFlag = false;
        if (analex.Preanalisis().getNombre() == Token.HELP) {
            match(Token.HELP);
        } else {
            match(Token.FUNC);
            acomp();
        }
    }

    public void acomp() {
        if (analex.Preanalisis().getNombre() == Token.CA) {
            match(Token.CA);
            parametros();
            match(Token.CC);
        } else if (analex.Preanalisis().getNombre() == Token.HELP) {
            match(Token.HELP);
        }
    }

    public void parametros() {
        param();
        masparam();
    }

    public void param() {
        switch (analex.Preanalisis().getNombre()) {
            case Token.STRING:
                match(Token.STRING);
                break;
            case Token.NUM:
                match(Token.NUM);
                break;
            case Token.TRUE:
                match(Token.TRUE);
                break;
            case Token.FALSE:
                match(Token.FALSE);
                break;
            default:
                match(Token.GB);
                break;
        }
    }

    public void masparam() {
        if (analex.Preanalisis().getNombre() == Token.COMA) {
            match(Token.COMA);
            param();
            masparam();
        }
    }

    public void match(int token) {
//        System.out.println("token: " + token);
//        System.out.println("token.getNombre(): " + analex.Preanalisis().getNombre());
        
        if (analex.Preanalisis().getNombre() == token) {
            analex.Avanzar();
        } else {
            System.out.println("Error en Parser al procesar Token!");
            this.errorFlag = true;
        }
    }

    public void Init() {
        analex.Init();
    }
}
