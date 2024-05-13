package ict.finki.store26springapi.service.impl;

import ict.finki.store26springapi.model.OrderItem;
import ict.finki.store26springapi.model.Product;
import ict.finki.store26springapi.model.Size;
import ict.finki.store26springapi.model.dto.SizeDto;
import ict.finki.store26springapi.model.exceptions.ProductNotFoundException;
import ict.finki.store26springapi.model.exceptions.SizeNotFoundException;
import ict.finki.store26springapi.repository.OrderItemRepository;
import ict.finki.store26springapi.repository.SizeRepository;
import ict.finki.store26springapi.service.ProductService;
import ict.finki.store26springapi.service.SizeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SizeServiceImpl implements SizeService {

    private final SizeRepository sizeRepository;
    private final ProductService productService;

    private final OrderItemRepository orderItemRepository;

    public SizeServiceImpl(SizeRepository sizeRepository, ProductService productService, OrderItemRepository orderItemRepository) {
        this.sizeRepository = sizeRepository;
        this.productService = productService;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public Optional<Size> findById(Long id) {
        return sizeRepository.findById(id);
    }

    @Override
    public List<Size> findAllByProduct(Long productId) {
        return sizeRepository.findAllByProductId(productId);
    }

    @Override
    public Optional<Size> save(Long productId, SizeDto sizeDto) {
        Product product = productService.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        Size size = new Size();

        size.setName(sizeDto.getName());
        size.setQuantity(sizeDto.getQuantity());
        size.setProduct(product);

        return Optional.of(sizeRepository.save(size));
    }

    @Override
    public Optional<Size> edit(Long id, SizeDto sizeDto) {
        Size size = this.findById(id)
                .orElseThrow(() -> new SizeNotFoundException(id));

        size.setName(sizeDto.getName());
        size.setQuantity(sizeDto.getQuantity());

        return Optional.of(sizeRepository.save(size));
    }

    @Override
    public void deleteById(Long id) {
        sizeRepository.deleteById(id);
    }

    @Override
    public void updateSize(Long id, int quantity) {
        Size size = this.findById(id)
                .orElseThrow(() -> new SizeNotFoundException(id));

        size.setQuantity(size.getQuantity()-quantity);

        sizeRepository.save(size);

    }
}
