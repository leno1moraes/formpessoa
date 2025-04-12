export function formatarTelefoneInput(valor: string): string {
    const telefone = valor.replace(/\D/g, '');
  
    if (telefone.length <= 2) {
      return `(${telefone}`;
    } else if (telefone.length <= 6) {
      return telefone.replace(/(\d{2})(\d+)/, '($1) $2');
    } else if (telefone.length <= 10) {
      return telefone.replace(/(\d{2})(\d{5})(\d{0,4})/, '($1) $2-$3');
    } else {
      return telefone.slice(0, 11).replace(/(\d{2})(\d{5})(\d{4})/, '($1) $2-$3');
    }
  }
  