package com.du.forpet.controller;

import com.du.forpet.domain.dto.PetResponseDto;
import com.du.forpet.domain.dto.PetSaveRequestDto;
import com.du.forpet.domain.dto.PetUpdateRequestDto;
import com.du.forpet.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/pets")
@RequiredArgsConstructor
@RestController
public class PetController {

    private final PetService petService;

    @PostMapping("/")
    public Long register(@RequestBody PetSaveRequestDto requestDto) {
        return petService.save(requestDto);
    }

    @GetMapping("/{id}")
    public PetResponseDto findById(@PathVariable Long id) {
        return petService.findById(id);

    }

    @GetMapping("/")
    public List<PetResponseDto> findAll() {
        return petService.findAll();
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody PetUpdateRequestDto requestDto) {
        return petService.updateInfo(id, requestDto);

    }

    @PatchMapping("/status/{id}")
    public void inactivate(@PathVariable Long id) {
        petService.delete(id);
    }

}
