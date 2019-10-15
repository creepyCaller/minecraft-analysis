import net.minecraft.client.main.Main;

/**
 * Minecraft客户端启动器
 * 需要设置的参数（其中路径需要依据实际情况修改）：
 * VM options:
 * -XX:+UnlockExperimentalVMOptions -XX:+UseG1GC -XX:G1NewSizePercent=20 -XX:G1ReservePercent=20 -XX:MaxGCPauseMillis=50 -XX:G1HeapRegionSize=16M -XX:-UseAdaptiveSizePolicy -XX:-OmitStackTraceInFastThrow -Xmn128m -Xmx2048m -Dfml.ignoreInvalidMinecraftCertificates=true -Dfml.ignorePatchDiscrepancies=true -XX:HeapDumpPath=MojangTricksIntelDriversForPerformance_javaw.exe_minecraft.exe.heapdump -Djava.library.path=1.12-natives -Dminecraft.launcher.brand=MCP -Dminecraft.launcher.version=9.40
 * Program arguments:
 * --width 854 --height 480 --username creepyCaller --version "MCP 9.40" --assetsDir assets --assetIndex 1.12 --uuid 0 --accessToken 0 --userType mojang --versionType release
 */
public class Start
{
    public static void main(String[] args)
    {
        Main.main(args);
    }
}
