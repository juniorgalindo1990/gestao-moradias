export interface Residence {
  id: number;
  descricao: string;
  tipo: string;
  finalidade: string;
  logradouro: string;
  bairro: string;
  cidade: string;
  estado: string;
  cep: string;
  wifi: boolean;
  garagem: boolean;
  mobiliado: boolean;
  banheiroPrivativo: boolean;
  valorAluguel: number;
  nomeProprietario: string;
  emailProprietario: string;
  fotos: string[];
}
