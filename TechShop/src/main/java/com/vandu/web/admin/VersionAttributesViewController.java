package com.vandu.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vandu.model.ProductVersion;
import com.vandu.service.ProductVersionService;
import com.vandu.service.VersionAttributesService;

@Controller
@RequestMapping("/admin/version-attributes")
public class VersionAttributesViewController {
	
	@Autowired
	private VersionAttributesService versionAttributesService;
	
	@Autowired
	private ProductVersionService productVersionService;

	
	@GetMapping("list/{versionId}")
	public String list(@PathVariable("versionId")Long versionId,ModelMap modelMap) {
		ProductVersion productVersion = productVersionService.findById(versionId).orElse(null);
		
		if(productVersion!=null) {
			modelMap.addAttribute("versionId",versionId );
			modelMap.addAttribute("productId", productVersion.getProduct().getProductId());
			return "admin/versionAttributes";
		}
		
		return "404";
	}
}
