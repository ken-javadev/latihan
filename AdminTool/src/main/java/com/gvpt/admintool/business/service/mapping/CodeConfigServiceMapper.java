/*
 * Created on 7 Oct 2017 ( Time 18:41:59 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.gvpt.admintool.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.gvpt.admintool.bean.CodeConfig;
import com.gvpt.admintool.bean.jpa.CodeConfigEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class CodeConfigServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public CodeConfigServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'CodeConfigEntity' to 'CodeConfig'
	 * @param codeConfigEntity
	 */
	public CodeConfig mapCodeConfigEntityToCodeConfig(CodeConfigEntity codeConfigEntity) {
		if(codeConfigEntity == null) {
			return null;
		}

		//--- Generic mapping 
		CodeConfig codeConfig = map(codeConfigEntity, CodeConfig.class);

		return codeConfig;
	}
	
	/**
	 * Mapping from 'CodeConfig' to 'CodeConfigEntity'
	 * @param codeConfig
	 * @param codeConfigEntity
	 */
	public void mapCodeConfigToCodeConfigEntity(CodeConfig codeConfig, CodeConfigEntity codeConfigEntity) {
		if(codeConfig == null) {
			return;
		}

		//--- Generic mapping 
		map(codeConfig, codeConfigEntity);

	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}