package org.api.repositories;

import org.api.models.AnimacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnimacaoRepository extends JpaRepository<AnimacaoModel, UUID> {

}
