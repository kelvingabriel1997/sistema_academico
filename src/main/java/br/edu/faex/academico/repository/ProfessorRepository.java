package br.edu.faex.academico.repository;


import br.edu.faex.academico.config.Conexao;
import br.edu.faex.academico.model.Professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorRepository {
    private  List <Professor> professores = new ArrayList<>();

    public void salvar(Professor professor){
        //alunos.add(aluno);
        String sql = "insert into professores (nome, email, id) VALUES (?, ?, ?)";
        try {
            Connection conexao = Conexao.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql); //Statement = comando/instrução
            comando.setString(1, professor.getNome());
            comando.setString(2, professor.getEmail());
            comando.setBoolean(3, professor.isAtivo());
            comando.executeUpdate();
            comando.close();
            conexao.close();
            System.out.println("Professor cadastrado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar:" + e.getMessage());
        }
    }

    public List<Professor> listar(){
        //return alunos;
        List<Professor> professores = new ArrayList<>();
        String sql = "SELECT * FROM professores";
        try {
            Connection conexao = Conexao.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql);
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()){
                Professor professor = new Professor();
                professor.setId(resultado.getLong("id"));
                professor.setNome(resultado.getString("nome"));
                professor.setEmail(resultado.getString("email"));
                professor.setAtivo(resultado.getBoolean("ativo"));
                professores.add(professor);
            }
            resultado.close();
            comando.close();
            conexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
        return professores;

    }

    public Professor buscarPorId(Long id){
        String sql = "SELECT * FROM professores WHERE id = ?";
        try {
            Connection conexao = Conexao.conectar();
            PreparedStatement comando =  conexao.prepareStatement(sql);
            comando.setLong(1, id);
            ResultSet resultado = comando.executeQuery();
            if(resultado.next()){
                Professor professor = new Professor();
                professor.setId(resultado.getLong("id"));
                professor.setNome(resultado.getString("nome"));
                professor.setEmail(resultado.getString("email"));
                professor.setAtivo(resultado.getBoolean("ativo"));
                return professor;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
        }
        return null;
    }

    public void excluir(Long id){
        for (Professor professor : professores){
            if(professor.getId().equals(id)){
                professores.remove(professor);
                return;
            }
        }
    }

    public void atualizar(Professor alunoEditado){
        for (Professor professor : professores){
            if(professor.getId().equals(alunoEditado.getId())){
                professor.setNome(alunoEditado.getNome());
                professor.setEmail(alunoEditado.getEmail());
                return;
            }
        }
    }



}
