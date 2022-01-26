package com.du.forpet.controller.api;

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
public class PetApiController {

    private final PetService petService;

    @PostMapping()
    public Long register(@RequestBody PetSaveRequestDto requestDto) {
        return petService.save(requestDto);
    }

    @GetMapping("/{id}")
    public PetResponseDto findById(@PathVariable Long id) {
        return petService.findById(id);

    }

    @GetMapping()
    public List<PetResponseDto> findAll() {
        return petService.findAll();
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody PetUpdateRequestDto requestDto) {
        return petService.updateInfo(id, requestDto);

    }

    @PatchMapping("/{id}/withdrawal")
    public void withdraw(@PathVariable Long id) {
        petService.withdraw(id);
    }

}
