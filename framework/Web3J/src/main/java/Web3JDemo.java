import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.File;

/**
 * @author Crysmart
 * @date 2020/7/21 14:46
 */
public class Web3JDemo {
    public static void main(String[] args) throws Exception {
        Credentials credentials = WalletUtils.loadCredentials("123456", new File("D:/UTC--2020-07-21T07-25-35.4000000Z--3500ebc214cbb6ed7f079f771179a3166e9c3c5a.json"));

        //以太坊节点，这个节点失效了
        Web3j build = Web3j.build(new HttpService("https://rinkeby.infura.io/"));
        Web3ClientVersion send = build.web3ClientVersion().send();
        String web3ClientVersion = send.getWeb3ClientVersion();
        System.out.println(web3ClientVersion);
    }

    public static void function() throws Exception {
        //创建本地钱包
        String s = WalletUtils.generateNewWalletFile("123456", new File("D:/"));
        System.out.println(s);
    }
}
