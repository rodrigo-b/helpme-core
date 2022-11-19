package br.com.helpme.helpmecore.improvement.repository;

public interface ImprovementQueries {

    public static final String FIND_ALL_BY_USER_ID = "select imp from Improvement imp join imp.user where imp.user.idUser = :userId";
}
