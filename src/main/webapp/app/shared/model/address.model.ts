import { IContact } from 'app/shared/model/contact.model';

export interface IAddress {
  id?: number;
  country?: string;
  area?: string;
  city?: string;
  street?: string;
  number?: string;
  contact?: IContact;
}

export const defaultValue: Readonly<IAddress> = {};
