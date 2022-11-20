import { useState } from 'react';

export const useModal = (): [boolean, () => void, () => void] => {
  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  return [open, handleOpen, handleClose];
};
