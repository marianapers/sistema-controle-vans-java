package controlevans;

import java.util.HashMap;

public class SistemaDados {
    private static HashMap<String, Usuario> usuarios = new HashMap<>();
    
    public static void inicializarSistema() {
        criarUsuariosEVans();
    }
    
    private static void criarUsuariosEVans() {
        // Usuário Marcos
        Usuario marcos = new Usuario("Marcos", "123");
        marcos.adicionarVan(new Van("010", "ABC-1234", "Rafael"));
        marcos.adicionarVan(new Van("019", "DEF-5678", "Carlos"));
        marcos.adicionarVan(new Van("070", "GHI-9012", "Marcelo"));
        usuarios.put("Marcos", marcos);
        
        // Usuário Jhonatan
        Usuario jhonatan = new Usuario("Jhonatan", "123");
        jhonatan.adicionarVan(new Van("021", "XYZ-3456", "Joel"));
        jhonatan.adicionarVan(new Van("013", "LMN-7890", "Lucas"));
        jhonatan.adicionarVan(new Van("011", "OPQ-1122", "Diego"));
        usuarios.put("Jhonatan", jhonatan);
        
        // Usuário Pedro
        Usuario pedro = new Usuario("Pedro", "123");
        pedro.adicionarVan(new Van("012", "RST-3344", "Luisa"));
        pedro.adicionarVan(new Van("023", "UVW-5566", "Manuel"));
        pedro.adicionarVan(new Van("018", "JKL-7788", "João"));
        usuarios.put("Pedro", pedro);
        
        // Usuário Mariana
        Usuario mariana = new Usuario("Mariana", "123");
        mariana.adicionarVan(new Van("001", "BCD-9900", "Luan"));
        mariana.adicionarVan(new Van("009", "EFG-1010", "Miguel"));
        mariana.adicionarVan(new Van("060", "HIJ-2020", "Kaike"));
        usuarios.put("Mariana", mariana);
    }
    
    public static Usuario getUsuario(String nome) {
        return usuarios.get(nome);
    }
    
    public static boolean validarLogin(String nome, String senha) {
        Usuario usuario = usuarios.get(nome);
        return usuario != null && usuario.getSenha().equals(senha);
    }
}