package com.gxdxx.programadmin.repository;

import com.gxdxx.programadmin.entity.Post;
import com.gxdxx.programadmin.entity.QPost;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface PostRepository extends
        JpaRepository<Post, Long>,
        QuerydslPredicateExecutor<Post>,
        QuerydslBinderCustomizer<QPost> {

    @Override
    default void customize(QuerydslBindings bindings, QPost root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.title, root.hashtag, root.createdBy, root.createdAt);
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
    }

}
