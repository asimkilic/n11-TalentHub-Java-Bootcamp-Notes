package com.asimkilic.springboot.springboottraining.converter;

import com.asimkilic.springboot.springboottraining.dto.KategoriDto;
import com.asimkilic.springboot.springboottraining.entity.Kategori;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface KategoriConverter {

    KategoriConverter INSTANCE = Mappers.getMapper(KategoriConverter.class);

    @Mapping(source = "ustKategori.id", target = "ustKategoriId")
    KategoriDto convertKategoriToKategoriDto(Kategori kategori);

    @Mapping(source = "ustKategoriId", target = "ustKategori.id")
    Kategori convertKategoriDtoToKategori(KategoriDto kategoriDto);

    @Mapping(source = "ustKategori.id", target = "ustKategoriId")
    List<KategoriDto> convertAllKategoriListToKategoriDtoList(List<Kategori> kategoriList);

}
