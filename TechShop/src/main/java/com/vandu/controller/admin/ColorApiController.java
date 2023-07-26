package com.vandu.controller.admin;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vandu.dto.ColorDto;
import com.vandu.model.Color;
import com.vandu.service.ColorService;

@RestController
@RequestMapping("/admin/color")
public class ColorApiController {

	@Autowired
	private ColorService colorService;

	@PostMapping("saveOrUpdate")
	public ResponseEntity<ColorDto> saveOrUpdate(@RequestBody ColorDto colorDto) {
		Color color = new Color();

		BeanUtils.copyProperties(colorDto, color);

		Color entity = colorService.save(color);

		BeanUtils.copyProperties(entity, colorDto);

		return new ResponseEntity<>(colorDto, HttpStatus.OK);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		Color color = colorService.findById(id).orElse(new Color());

		if (color != null) {
			colorService.delete(color);
		}

		return ResponseEntity.ok("Success");
	}

	@GetMapping("find/{id}")
	public ResponseEntity<ColorDto> findById(@PathVariable("id") Long id) {
		ColorDto colorDto = new ColorDto();
		BeanUtils.copyProperties(colorService.findById(id).get(), colorDto);

		return ResponseEntity.ok(colorDto);
	}

	@GetMapping("getAll")
	public ResponseEntity<Page<Color>> getAll(@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, @RequestParam("keyword") String keyword) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(3);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);

		Page<Color> list = keyword.equals("") ? colorService.findAll(pageable)
				: colorService.findByNameContaining(keyword, pageable);

		return ResponseEntity.ok(list);

	}

	@GetMapping("getAllColor")
	public ResponseEntity<?> getAllColor() {
		return ResponseEntity.ok(colorService.findAll().stream().map(item -> {
			ColorDto colorDto = new ColorDto();

			BeanUtils.copyProperties(item, colorDto);

			return colorDto;
		}).toList());
	}

}
