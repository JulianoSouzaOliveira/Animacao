package org.api.services;

import org.api.models.AnimacaoModel;
import org.api.repositories.AnimacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AnimacaoServices {
    final AnimacaoRepository animacaoRepository;

    public AnimacaoServices(AnimacaoRepository animacaoRepository) {
        this.animacaoRepository = animacaoRepository;
    }

    public void save(AnimacaoModel animacaoModel) {
        animacaoRepository.save(animacaoModel);
    }

    public List<AnimacaoModel> findAll() {
        return animacaoRepository.findAll();
    }

    public Optional<AnimacaoModel> findById(UUID id) {
        return animacaoRepository.findById(id);
    }

    public void delete(AnimacaoModel animacaoModel) {
        animacaoRepository.delete(animacaoModel);
    }
}
