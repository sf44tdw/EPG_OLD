/*
 * 指定されたディレクトリ下のファイルをリストアップする。
 */
package epgtools.libepgupdate.listmaker.fileseeker;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

/**
 *
 */
public class FileSeeker {

    private final File SourceDir;

    //検索対象とする条件の一覧
    private final IOFileFilter seekType;

    private IOFileFilter dirf = TrueFileFilter.INSTANCE;

    public FileSeeker(File SourceDir, IOFileFilter seekType) {
        this.SourceDir = new File(SourceDir.getAbsolutePath());
        this.seekType = seekType;
    }

    public synchronized boolean isRecursive() {
        boolean Ret = false;
        if (this.dirf instanceof TrueFileFilter) {
            Ret = true;
        } else if (this.dirf instanceof FalseFileFilter) {
            Ret = false;
        }
        return Ret;
    }

    /**
     * サブディレクトリも検索するか。trueをセットすると検索するようになる。デフォルトはtrue
     *
     * @param recursive セット対象
     */
    public synchronized void setRecursive(boolean recursive) {
        if (recursive == false) {
            this.dirf = FalseFileFilter.INSTANCE;
        } else {
            this.dirf = TrueFileFilter.INSTANCE;
        }
    }

    /**
     * 検索を行い、その結果を返す。
     *
     * @return 見つかったファイルを示すファイルオブジェクトのリスト 検索先がディレクトリではなかった場合はnull
     */
    public synchronized List<File> seek() {
        if (this.SourceDir.isDirectory()) {
            List<File> list = Collections.synchronizedList(new ArrayList<File>());
            Collection<File> files = FileUtils.listFiles(this.SourceDir, this.seekType, this.dirf);
            list.addAll(files);
            return Collections.unmodifiableList(list);
        } else {
            return null;
        }
    }
}
