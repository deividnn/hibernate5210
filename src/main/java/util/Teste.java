/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entidades.Pessoa;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author deividnn
 */
public class Teste {

    public static void main(String[] args) {
        HibernateUtil.getSessionFactory();
        Teste t = new Teste();
        t.inserirPessoas(10, 1);
        t.inserirPessoas(20, 2);
        t.inserirPessoas(30, 3);
        t.inserirPessoas(40, 4);
        t.inserirPessoas(50, 5);
        t.inserirPessoas(60, 6);
        t.inserirPessoas(10, 1);
        t.inserirPessoas(20, 2);
        t.inserirPessoas(30, 3);
        t.inserirPessoas(40, 4);
        t.inserirPessoas(50, 5);
        t.inserirPessoas(60, 6);
        t.inserirPessoas(10, 1);
        t.inserirPessoas(20, 2);
        t.inserirPessoas(30, 3);
        t.inserirPessoas(40, 4);
        t.inserirPessoas(50, 5);
        t.inserirPessoas(60, 6);
        HibernateUtil.shutdown();
        System.exit(0);

    }

    public void inserirPessoas(int n, int ic) {
        boolean sucesso = true;
        Session sessao = HibernateUtil.sessionFactory.getCurrentSession();
        Transaction tx = sessao.beginTransaction();
        try {

            System.out.println("" + ic + " inserindo " + n + " pessoas e fazendo consultas com/sem cache");

            for (int i = 0; i < n; i++) {
                Pessoa p = new Pessoa();
                p.setNome("pessoa" + (i + n));
                sessao.saveOrUpdate(p);
            }

            tx.commit();
            SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = sessao.beginTransaction();
            int seg = 100;
            int cont = 0;
            boolean ok = true;
            while (ok) {
                System.out.println("" + ic + " consulta as " + sd.format(Calendar.getInstance().getTime()));
                sessao.createQuery("select vo from Pessoa vo").setCacheable(true).list();
                new Thread().sleep(seg);
                seg -= 10;
                if (seg == 0) {
                    seg = 100;
                    cont++;
                }
                if (cont > 5) {
                    ok = false;
                }

                sessao.createQuery("select vo from Pessoa vo").list();
            }

        } catch (Exception e) {
            sucesso = false;
            e.printStackTrace();
        } finally {
            sessao.close();
        }

    }

}
