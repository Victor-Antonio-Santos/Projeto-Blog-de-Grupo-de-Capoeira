package br.com.capoeiramundial.service;
import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Service; import org.springframework.util.StringUtils; import org.springframework.web.multipart.MultipartFile; import java.io.*; import java.nio.file.*; import java.util.*;
@Service
public class StorageService {
  private final Path root; private static final Set<String> IMAGES=Set.of("jpg","jpeg","png","webp"), VIDEOS=Set.of("mp4");
  public StorageService(@Value("${app.upload-dir}") String dir) throws IOException { root=Paths.get(dir).toAbsolutePath().normalize(); Files.createDirectories(root); }
  public String store(MultipartFile file, boolean video) throws IOException {
    if(file==null||file.isEmpty()) return null; String original=StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
    String ext=original.contains(".")?original.substring(original.lastIndexOf('.')+1).toLowerCase():"";
    if(!(video?VIDEOS:IMAGES).contains(ext)) throw new IllegalArgumentException("Tipo de arquivo nao permitido.");
    String name=UUID.randomUUID()+"."+ext; Path target=root.resolve(name).normalize(); if(!target.startsWith(root)) throw new SecurityException("Caminho invalido");
    try(InputStream in=file.getInputStream()){Files.copy(in,target,StandardCopyOption.REPLACE_EXISTING);} return "uploads/"+name;
  }
}
