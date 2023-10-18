package org.sberinsur.tm.beans.entity;

import lombok.*;
import org.sberinsur.tm.dto.entity.test.DumpEntity;
import org.sberinsur.tm.dto.entity.test.EntityEntity;

import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * Класс-обертка для файла, используется для передачи в метод сопутсвующих параметров.
 * @author Ненароков П.Ю.
 * @author Софронов И.Е.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoxedFile {
    /**Файл, с которым работает система*/
    private File workFile;
    /**Путь к файлу в директории загрузок*/
    private String originalFile;
    /**Маска файла*/
    private EntityEntity validatedEntity;
    /**Промежуточное содержание файла*/
    private List<String[]> content;
    /**Аналитика*/
    private DumpEntity dump;
    /**GUID файла*/
    private UUID guidOfFile;
    /**GUID поставки*/
    private UUID guidOfDelivery;
    /**Флаг, показывающий, файл workFile получен из архива (true) или нет (false)*/
    private boolean fromArchive;

    /**
     * @param file файл, который необходимо обернуть.
     * @param guidOfDelivery guid поставки.
     * @param archive архив, из которого взят файл (может быть null).
     */
    public BoxedFile(File file, UUID guidOfDelivery, BoxedFile archive) {
        workFile = file;
        originalFile = file.getAbsolutePath();
        guidOfFile = UUID.randomUUID();
        this.guidOfDelivery = guidOfDelivery;
        dump = new DumpEntity();
        dump.setFileGuid(guidOfFile);
        dump.setDeliveryGuid(guidOfDelivery);
        if (archive != null) {
            dump.setArchiveName(archive.workFile.getName());
            dump.setArchiveSize(archive.getWorkFile().getTotalSpace());
            fromArchive = true;
        }
    }
}
